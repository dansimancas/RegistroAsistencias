<?php

namespace App\Http\Controllers;

use App\AttendanceModel;
use App\Http\Requests;
use Validator,
    Input,
    Redirect;
use Illuminate\Http\Request;

class AttendanceController extends Controller {

    /**
     * Display a listing of the resource.
     *
     * @return Response
     */
    public function index() {
        $attendance = AttendanceModel::all();

        $object = array();

        foreach ($attendance as $value) {
            $var = array(
                "id" => $value["ID"],
                "student_id" => $value["STUDENTID"],
                "nrc" => $value["NRC"],
                "attendance" => $value["ATTENDANCE"],
                "created_at" => $value["created_at"]->toDateTimeString(),
                "updated_at" => $value['updated_at']->toDateTimeString()
            );
            $object[] = $var;
        }

        return response()->json($object);
    }

    /**
     * Store a newly created resource in storage.
     * @return Response
     */
    public function store() {
        $json  = Input::json()->all();

        $size =  sizeof($json['estudiantes']);
        for($i=0;$i<$size;$i++){
            $attendance = new AttendanceModel;
            $attendance->NRC            = $json['nrc'];
            $attendance->STUDENTID      = $json['estudiantes'][$i]['id'];
            $attendance->ATTENDANCE     = $json['estudiantes'][$i]['attendance'];
            $attendance->save();
        }
        //return "Registro Creado";

        //generacion de alarmas
        $alarm =  new AlarmsController();
        $alarm = $alarm->createAlarm($json['nrc']);
        return $alarm;
    }

    /**
     * @param $NRC
     * @return \Symfony\Component\HttpFoundation\Response
     */
    /* public function show($NRC)
      {
      $attendance = AttendanceModel::where('NRC', "=", $NRC)->get();
      $object = array(
      "nrc" => $attendance[0]["NRC"]
      );

      foreach($attendance as $value){
      $var = array(
      "id" => $value["ID"],
      "student_id" => $value["STUDENTID"],
      "attendance" => $value["ATTENDANCE"],
      "created_at" => $value["created_at"]->toDateTimeString(),
      "updated_at" => $value['updated_at']->toDateTimeString()
      );
      $object["students"][] = $var;
      }

      return response()->json($object);
      } */

    /* public function show($id)
      {
      $attendance = Attendance::where("StudentId", "=", $id)->get();
      return response()->json($id);
      } */
}
