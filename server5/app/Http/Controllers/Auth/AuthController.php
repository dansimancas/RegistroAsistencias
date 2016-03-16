<?php namespace App\Http\Controllers\Auth;

use App\Http\Controllers\Controller;
use App\StudentsModel;
use App\TeachersModel;
use Request;
use App\TokenModel;

class AuthController extends Controller
{

    /*
    |--------------------------------------------------------------------------
    | Token Generator and Checker
    |--------------------------------------------------------------------------
    |
    | This controller handles the registration of new users, as well as the
    | authentication of existing users. By default, this controller uses
    | a simple trait to add these behaviors. Why don't you explore it?
    |
    */

    public function token()
    {

        $username = Request::input('username');
        $token = TokenModel::where("username", "=", $username)->first();

        $student = StudentsModel::where("ID", "=", $username)->first();
        if ($student) {
            $type = "student";
        } else {
            $teacher = TeachersModel::where("ID", "=", $username)->first();
            if ($teacher) {
                $type = "teacher";
            }
        }


        if ($student) {

        } else if ($teacher) {

        } else {
            $type = "undefined";
        }

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
