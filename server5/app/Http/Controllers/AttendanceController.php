<?php namespace App\Http\Controllers;
use App\AttendanceModel;
use App\Http\Requests;
use Validator, Input, Redirect;
use Illuminate\Http\Request;

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
        /*$json  = Input::json()->all();

        $size =  sizeof($json[0]['ESTUDIANTES']);
        for($i=0;$i<$size;$i++){
            $attendance = new AttendanceModel;
            $attendance->NRC       = $json[0]['NRC'];
            $attendance->STUDENTID      = $json[0]['ESTUDIANTES'][$i]['ID'];
            $attendance->ATTENDANCE     = $json[0]['ESTUDIANTES'][$i]['ATTENDANCE'];
            $attendance->save();
        }*/

        //$alarm =  new AlarmsController();
        //$alarm = $alarm->createAlarm($json[0][$json[0]['NRC']);


        $attendance = new AttendanceModel;
        $attendance->STUDENTID      = Input::get('STUDENTID');
        $attendance->NRC       = Input::get('NRC');
        $attendance->ATTENDANCE     = Input::get('ATTENDANCE');
        $attendance->save();

        //generacion de alarmas
        $alarm =  new AlarmsController();
        $alarm = $alarm->createAlarm(Input::get('NRC'));

       //return 'ok, registro creado';
        return $alarm;

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