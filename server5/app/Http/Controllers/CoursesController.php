<?php namespace App\Http\Controllers;

use App\Http\Requests;
use App\Http\Controllers\Controller;

use App\SiriusCourseModel;
use Illuminate\Http\Request;

class CoursesController extends Controller {

	/**
	 * Display the specified resource.
	 *
	 * @param  int  $id
	 * @return Response
	 */
	public function showCoursesByTeacher($id)
	{

        //dd($id);

        $datos = SiriusCourseModel::where("DOCENTEID", "=", $id)->get();

        return response()->json($datos);
	}


}
