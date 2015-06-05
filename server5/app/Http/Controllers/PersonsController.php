<?php namespace App\Http\Controllers;

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


    public function showStudentsInfo($id)
    {
        $student = StudentsModel::where("ID", "=", $id)->get();
        $object = array(
            "id" => $student[0]["ID"],
            "names" => $student[0]["NOMBRES"],
            "lastnames" => $student[0]["APELLIDOS"],
            "program" => $student[0]["PROGRAMA"],
            "email" => $student[0]["EMAIL"],
            "links" => array(
                "student_uri" => "/student/".$student[0]["ID"]."/courses",
                "attendance_uri" => "/student/".$student[0]["ID"]."/attendance",
            )

        );

        return response()->json($object);
    }

    /**
     * Funcion para mostrar toda la informaciÃ³n de un profesor, pasando como referencia el ID
     * @param $id
     * @return \Symfony\Component\HttpFoundation\Response
     */
    public function showTeachersInfo($id)
    {
        $data = TeachersModel::where("ID", "=", $id)->get();
        $object = array(
            "id" => $data[0]["ID"],
            "names" => $data[0]["NOMBRES"],
            "lastnames" => $data[0]["APELLIDOS"],
            "type" => $data[0]["TIPODOCENTE"],
            "school" => $data[0]["ESCUELA"],
            "department" => $data[0]["DEPARTAMENTO"],
            "email" => $data[0]["EMAIL"],
            "resource_uri" => "/teacher/".$data[0]["ID"]."/courses",
        );
        return response()->json($object);
    }
}
