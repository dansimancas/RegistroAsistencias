<?php namespace App\Http\Controllers;

use App\Http\Requests;
use App\Http\Controllers\Controller;

use App\SiriusCourseModel;
use App\SiriusCoursesModel;
use App\SiriusSavioExternalModel;
use Illuminate\Http\Request;

class CoursesController extends Controller {

	/**
	 * Display the specified resource.
	 *
	 * @param  int  $id
	 * @return Response
	 */
	/*
	 * Funcion para listar los cursos de un profesor con su codigo
	 * */
    public function showCoursesByTeacher($id)
	{
        $data = SiriusCoursesModel::where("DOCENTEID", "=", $id)->get();

        return response()->json($data);
	}
    /*
     * Funcion para listar los alumnos de un curso, pasando como referencia el NRC_PERIODO_KEY
     * */
    public function showStudentsByCourse($NRC)
    {
        $data = SiriusSavioExternalModel::where("IDNUMBER", "=", $NRC)->get();

        return response()->json($data);
    }
}
