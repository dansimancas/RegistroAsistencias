<?php namespace App\Http\Controllers;

use App\Http\Requests;
use App\CoursesByTeacherModel;
use App\CoursesByStudentModel;
use App\StudentsByCourseModel;
use App\CoursesModel;
use Illuminate\Http\Request;

class CoursesController extends Controller {

<<<<<<< HEAD
    /*
     * Función para mostrar toda la información de un curso, pasando como referencia el NRC_PERIODO_KEY
     */
    public function showCoursesInfo($NRC)
    {
        $data = CoursesModel::where("NRC_PERIODO_KEY", "=", $NRC)->get();

        return response()->json($data);
    }

    /*
     * Función para listar los cursos de un profesor, pasando como referencia el TEACHERID
=======
    /**
     * Funcion para listar los cursos de un profesor con su codigo.
     * @param $id
     * @return \Symfony\Component\HttpFoundation\Response
>>>>>>> origin/master
     */
    public function showCoursesByTeacher($id)
    {
        $data = CoursesByTeacherModel::where("TEACHERID", "=", $id)->get();

        return response()->json($data);
	}

<<<<<<< HEAD
    /*
     * Función para listar los alumnos de un curso, pasando como referencia el NRC_PERIODO_KEY
=======
    /**
     * Funcion para listar los alumnos de un curso, pasando como referencia el NRC
     * @param $NRC
     * @return \Symfony\Component\HttpFoundation\Response
>>>>>>> origin/master
     */
    public function showStudentsByCourse($NRC)
    {
        $data = StudentsByCourseModel::where("NRC", "=", $NRC)->get();

        return response()->json($data);
    }

<<<<<<< HEAD
    /*
     * Función para mostrar los cursos de un estudiante, pasando como referencia el STUDENTID
=======
    /**
     * Funcion para listar los cursos de un estudiante, pasando como referencia su código.
     * @param $id
     * @return \Symfony\Component\HttpFoundation\Response
>>>>>>> origin/master
     */
    public function showCoursesByStudent($id)
    {
        $data = CoursesByStudentModel::where("STUDENTID", "=", $id)->get();

        return response()->json($data);
    }

}
