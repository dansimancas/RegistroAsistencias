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
                "id" => $student["ID"],
                "names" => $student["NOMBRES"],
                "lastnames" => $student["APELLIDOS"],
                "program" => $student["PROGRAMA"],
                "email" => $student["EMAIL"],
                "links" => array(
                    "student_uri" => "/student/" . $student["ID"] . "/courses",
                    "attendance_uri" => "/student/" . $student["ID"] . "/attendance",
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
                "id" => $teacher["ID"],
                "names" => $teacher["NOMBRES"],
                "lastnames" => $teacher["APELLIDOS"],
                "type" => $teacher["TIPODOCENTE"],
                "school" => $teacher["ESCUELA"],
                "department" => $teacher["DEPARTAMENTO"],
                "email" => $teacher["EMAIL"],
                "resource_uri" => "/teacher/" . $teacher["ID"] . "/courses",
            );
        } else{
            $object = "No existe ningún profesor con el ID " . $id . ".";
        }

        return response()->json($object);
    }

}
