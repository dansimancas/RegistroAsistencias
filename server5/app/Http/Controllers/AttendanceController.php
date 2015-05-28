<?php namespace App\Http\Controllers;
use App\AttendanceModel;
use App\Http\Requests;
use App\Http\Controllers\Controller;
use Validator, Input, Redirect;
use Illuminate\Http\Request;
use Symfony\Component\HttpFoundation\Tests\ServerBagTest;
class AttendanceController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return Response
     */

   /* public function index()
    {
        $attendance = AttendanceModel::all();

        $object = array();

        foreach($attendance as $value){
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


    }*/

    /**
     * Store a newly created resource in storage.
     * @return Response
     */
    public function store()
    {
        $json  = Input::json()->all();

        $size =  sizeof($json[0]['ESTUDIANTES']);
        for($i=0;$i<$size;$i++){
            $attendance = new AttendanceModel;
            $attendance->NRC       = $json[0]['NRC'];
            $attendance->STUDENTID      = $json[0]['ESTUDIANTES'][$i]['ID'];
            $attendance->ATTENDANCE     = $json[0]['ESTUDIANTES'][$i]['ATTENDANCE'];
            $attendance->save();
        }
        return 'ok, registro creado';

    }

    /**
     * @param $NRC
     * @return \Symfony\Component\HttpFoundation\Response
     */
    /*public function show($NRC)
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
    }*/

    /*public function show($id)
    {
        $attendance = Attendance::where("StudentId", "=", $id)->get();
        return response()->json($id);
    }*/

}