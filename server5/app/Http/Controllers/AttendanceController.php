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
    public function index()
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


    }




    /**
     * Store a newly created resource in storage.
     *
     * @return Response
     */
    public function store()
    {
        // validate
        // read more on validation at http://laravel.com/docs/validation
        $rules = array(
            'STUDENTID'     => 'required',
            'NRC'      => 'required',
            'ATTENDANCE'    => 'required|numeric'
        );


        $validator = Validator::make(Input::all(), $rules);

        // process the login
        if ($validator->fails()) {
            return 'fallo';
        } else {
            // store
            $attendance = new AttendanceModel;

            $attendance->STUDENTID      = Input::get('STUDENTID');
            $attendance->NRC       = Input::get('NRC');
            $attendance->ATTENDANCE     = Input::get('ATTENDANCE');
            $attendance->save();

            return 'ok, registro creado';
        }

    }
    /*public function store()
    {
        echo 'Informacion';
        // validate
        // read more on validation at http://laravel.com/docs/validation
        $rules = array(
            'StudentId' => 'required',
            'CourseId' => 'required',
            'Attendance' => 'required|numeric',
            //'Date' => 'required'
        );
        $validator = Validator::make(Input::all(), $rules);
        // process the login
        if ($validator->fails()) {
            return 'Falla';
        } else {
            // store
            $attendance = new Attendance;
            $attendance->StudentId = Input::get('StudentId');
            $attendance->CourseId = Input::get('CourseId');
            $attendance->Attendance = Input::get('Attendance');
            //$attendance->Date = Input::get('Date');
            $attendance->save();
            return 'OK';
        }
    }*/
    /**
     * @param $NRC
     * @return \Symfony\Component\HttpFoundation\Response
     */
    public function show($NRC)
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
    }

    /*public function show($id)
    {
        $attendance = Attendance::where("StudentId", "=", $id)->get();
        return response()->json($id);
    }*/

}