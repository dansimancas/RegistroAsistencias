<?php

namespace App\Http\Controllers\Auth;

use App\Http\Controllers\Controller;
use App\StudentsModel;
use App\TeachersModel;
use Request;
use App\TokenModel;

class TokenController extends Controller {

    public function token() {
        $username = Request::input('username');
        $student = StudentsModel::where("ID", "=", $username)->first();
        if ($student) {
            $type = "student";
        } else {
            $teacher = TeachersModel::where("ID", "=", $username)->first();
            if ($teacher) {
                $type = "teacher";
            } else {
                $type = "undefined";
            }
        }
        $token = TokenModel::where("username", "=", $username)->first();
        if ($token == null) {
            $tokenNew = csrf_token();
            $token = new TokenModel;
            $token->USERNAME = $username;
            $token->TOKEN = $tokenNew;
            $token->save();
            $object = array(
                "type" => $type,
                "token" => $tokenNew
            );
        } else {
            $object = array(
                "type" => $type,
                "token" => $token->TOKEN
            );
        }
        return $object;
    }

}
