<?php

namespace App\Http\Controllers;

use App\Http\Requests;
use App\StudentsModel;
use App\TeachersModel;
use Illuminate\Http\Request;

class PersonsController extends Controller {

    /**
     * Funcion para mostrar toda la informacion de un estudiante, pasando como referencia el ID
     * @param $id
     * @return \Symfony\Component\HttpFoundation\Response
     */
    public function showStudentsInfo($id) {
        $student = StudentsModel::where("ID", "=", $id)->first();

        if ($student) {
            $object = array(
                "id" => $student[0]["ID"],
                "names" => $student[0]["NOMBRES"],
                "lastnames" => $student[0]["APELLIDOS"],
                "program" => $student[0]["PROGRAMA"],
                "email" => $student[0]["EMAIL"],
                "links" => array(
                    "student_uri" => "/student/" . $student[0]["ID"] . "/courses",
                    "attendance_uri" => "/student/" . $student[0]["ID"] . "/attendance",
                )
            );
        } else{
            $object = "No existe ningún estudiante con el ID " . $id . ".";
        }

        return response()->json($object);
    }

    /**
     * Funcion para mostrar toda la informaciÃ³n de un profesor, pasando como referencia el ID
     * @param $id
     * @return \Symfony\Component\HttpFoundation\Response
     */
    public function showTeachersInfo($id) {
        $teacher = TeachersModel::where("ID", "=", $id)->first();

        if ($teacher) {
            $object = array(
                "id" => $teacher[0]["ID"],
                "names" => $teacher[0]["NOMBRES"],
                "lastnames" => $teacher[0]["APELLIDOS"],
                "type" => $teacher[0]["TIPODOCENTE"],
                "school" => $teacher[0]["ESCUELA"],
                "department" => $teacher[0]["DEPARTAMENTO"],
                "email" => $teacher[0]["EMAIL"],
                "resource_uri" => "/teacher/" . $teacher[0]["ID"] . "/courses",
            );
        } else{
            $object = "No existe ningún profesor con el ID " . $id . ".";
        }

        return response()->json($object);
    }

}
