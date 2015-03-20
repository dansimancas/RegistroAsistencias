<?php namespace App\Http\Controllers;

use App\Attendance;
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
        $attendance = Attendance::all();
        return response()->json();
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return Response
     */
    public function create()
    {
        //
    }

    /**
     * Store a newly created resource in storage.
     *
     * @return Response
     */
    public function store()
    {
        echo 'Informacion';
        // validate
        // read more on validation at http://laravel.com/docs/validation
        $rules = array(
            'StudentId' => 'required',
            'CourseId' => 'required',
            'Attendance' => 'required|numeric',
            'Date' => 'required'
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
            $attendance->Date = Input::get('Date');
            $attendance->save();

            return 'OK';
        }


    }

    /**
     * Display the specified resource.
     *
     * @param  int $id
     * @return Response
     */
    public function show($id)
    {
        $attendance = Attendance::where("StudentId", "=", $id)->get();
        return response()->json($id);
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  int $id
     * @return Response
     */
    public function edit($id)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  int $id
     * @return Response
     */
    public function update($id)
    {
        //
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int $id
     * @return Response
     */
    public function destroy($id)
    {
        //
    }


}