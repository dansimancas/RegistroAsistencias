<?php
/**
 * Created by PhpStorm.
 * User: Laura Schiatti
 * Date: 25/03/2015
 * Time: 10:12 PM
 */

namespace App\Http\Controllers;


use App\AttendanceModel;
use App\StudentsByCourseModel;
use App\StudentsModel;
use App\CoursesByStudentModel;

class StatisticsController extends Controller {

    /**
     * Función para mostrar estadisticas de asistencia de un estudiante a un curso
     * @param $idstudent, $idcourse
     * @return \Symfony\Component\HttpFoundation\Response
     */
    public function showStatisticsByStudentByCourse($id, $NRC)
    {
        $data = AttendanceModel::where("STUDENTID", "=", $id)
                                ->Where("NRC", "=", $NRC)
                                ->get();
        $object = array(
            "student_id" => $data[0]["STUDENTID"],
            "nrc" => $data[0]["NRC"]
        );

        $came = 0;
        $notcame = 0;
        $arrivedlate = 0;
        $leftsoon = 0;
        $DK = 0;

        foreach($data as $value){

            $response = $value["ATTENDANCE"];
            switch ($response) {
                case 0:
                    $came += 1;
                    break;
                case 1:
                    $notcame += 1;
                    break;
                case 2:
                    $arrivedlate += 1;
                    break;
                case 3:
                    $leftsoon += 1;
                    break;
                case 4:
                    $DK += 1;
                    break;
            }
        }

        $total = $came + $notcame + $arrivedlate + $leftsoon + $DK;

        if($total!=0) {
            $object["came"] = $came * 100 / $total;
            $object["did_not_come"] = $notcame * 100 / $total;
            $object["arrived_late"] = $arrivedlate * 100 / $total;
            $object["left_soon"] = $leftsoon * 100 / $total;
            $object["undefined"] = $DK * 100 / $total;
        }else{
            $object["data"] = 0;
        }

        return response()->json($object);
    }

    public function showStatisticsByStudent($id)
    {
        $data = CoursesByStudentModel::where("STUDENTID", "=", $id)->get();

        $object = array(
            "student_id" => $data[0]["STUDENTID"],
            "courses" => array()
        );

        $i = 0;
        foreach ($data as $value) {
            //$NRC = $value["NRC"];
            $otherjson = $this->showStatisticsByStudentByCourse($data[0]["STUDENTID"], $value["NRC"]);
            //$otherjson = json_decode(self::showStatisticsByStudentByCourse($data[0]["STUDENTID"], $value["NRC"]));
            print_r ($otherjson);
            $var = array(
                "subject_name" => $value["SUBJECTNAME"],
                "nrc" => $value["NRC"],
                "attendance" => array(
                    "CAME(%)" => $otherjson["CAME(%)"],
                    "DID NOT COME(%)" => $otherjson["DID NOT COME(%)"],
                    "ARRIVED LATE(%)" => $otherjson["ARRIVED LATE(%)"],
                    "LEFT SOON(%)" => $otherjson["LEFT SOON(%)"],
                    "DK/DA(%)" => $otherjson["DK/DA(%)"]
                )
            );

            $var2[$i] = $var;
            $i+=1;
        }

        $object["courses"][]=$var2;
        return response()->json($object);

    }

    public function showStatisticsByCourse($NRC)
    {
        $data = StudentsByCourseModel::where("NRC", "=", $NRC)->get();

        //print_r ($data);

        $object = array(
            "nrc" => $data[0]["NRC"],
            "students" => array()
        );

        $i = 0;
        foreach ($data as $value) {
            //$NRC = $value["NRC"];
            $otherjson = $this->showStatisticsByStudentByCourse($value["STUDENTID"], $data[0]["NRC"]);

            //$array = json_decode(json_encode($otherjson), true); //convertir json a string
            //$array = (array) $otherjson;
            $string = json_decode($otherjson); //convierte el json en string
            $array = str_split($string); //convierte string a array

            print_r ($array);
            $var = array(
                "student_name" => $value["NAMES"],
                "student_lastname" => $value["LASTNAMES"],
                "attendance" => array(
                    "CAME(%)" => $array["CAME(%)"],
                    "DID NOT COME(%)" => $array["DID NOT COME(%)"],
                    "ARRIVED LATE(%)" => $array["ARRIVED LATE(%)"],
                    "LEFT SOON(%)" => $array["LEFT SOON(%)"],
                    "DK/DA(%)" => $array["DK/DA(%)"]
                )
            );

            $var2[$i] = $var;
            $i+=1;
        }

        $object["students"][]=$var2;
        return response()->json($object);

    }
}