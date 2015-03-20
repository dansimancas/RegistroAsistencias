<?php namespace App\Http\Controllers;

use App\Http\Requests;
use App\CoursesByTeacherModel;
use App\CoursesByStudentModel;
use App\StudentsByCourseModel;
use App\CoursesModel;
use Illuminate\Http\Request;

class CoursesController extends Controller {


    /**
     * Función para mostrar toda la información de un curso, pasando como referencia el NRC_PERIODO_KEY
     * @param $NRC
     * @return \Symfony\Component\HttpFoundation\Response
     */
    public function showCoursesInfo($NRC)
    {
        $data = CoursesModel::where("NRC_PERIODO_KEY", "=", $NRC)->get();

        return response()->json($data);
    }

    /**
     * Función para listar los cursos de un profesor, pasando como referencia el TEACHERID
     * @param $id
     * @return \Symfony\Component\HttpFoundation\Response
     */
    public function showCoursesByTeacher($id)
    {
        $data = CoursesByTeacherModel::where("TEACHERID", "=", $id)->get();

        $object = array(
            "TEACHERID" => $data[0]["TEACHERID"],
            "NAMES" => $data[0]["NAMES"],
            "LASTNAMES" => $data[0]["LASTNAMES"],
            "COURSES" => array()
        );

        foreach($data as $value){
            $var = array(
                "SUBJECTNAME" => $value["SUBJECTNAME"],
                "NRC" => $value["NRC"],
                "SECTION" => $value["SECTION"],
            );
            $object["COURSES"][] = $var;
        }

        return response()->json($object);

	}

    /**
     * Funcion para listar los alumnos de un curso, pasando como referencia el NRC
     * @param $NRC
     * @return \Symfony\Component\HttpFoundation\Response
     */
    public function showStudentsByCourse($NRC)
    {
        $data = StudentsByCourseModel::where("NRC", "=", $NRC)->get();

        $object = array(
            "NRC" => $data[0]["NRC"],
            "SUBJECT" => $data[0]["SUBJECT"],
            "TEACHERID" => $data[0]["TEACHERID"],
            "STUDENTS" => array()
        );

        foreach($data as $value){
            $var = array(
                "NAMES" => $value["NAMES"],
                "LASTNAMES" => $value["LASTNAMES"],
                "STUDENTID" => $value["STUDENTID"],
                "PROGRAM" => $value["PROGRAM"],
                "EMAIL" => $value["EMAIL"],
            );
            $object["STUDENTS"][] = $var;
        }

        return response()->json($object);
    }

    /**
     * Función para mostrar los cursos de un estudiante, pasando como referencia el STUDENTID
     * @param $id
     * @return \Symfony\Component\HttpFoundation\Response
     */
    public function showCoursesByStudent($id)
    {
        $data = CoursesByStudentModel::where("STUDENTID", "=", $id)->get();

        $object = array(
            "studentid" => $data[0]["STUDENTID"],
            "courses" => array()
        );

        foreach($data as $value){
            $var = array(
                "SUBJECTNAME" => $value["SUBJECTNAME"],
                "NRC" => $value["NRC"],
                "SECTION" => $value["SECTION"],
                "NAMES" => $value["NAMES"],
                "LASTNAMES" => $value["LASTNAMES"],
                "TEACHERID" => $value["TEACHERID"],

            );
            $object["COURSES"][] = $var;
        }

        return response()->json($object);
    }

}
