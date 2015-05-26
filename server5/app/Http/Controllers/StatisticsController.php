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
    public function showStatisticsByStudentByCourse($id, $NRC, $response=true)
    {
        $data = AttendanceModel::where("STUDENTID", "=", $id)
                                ->Where("NRC", "=", $NRC)
                                ->get()->toArray();
        $object = array(
            "student_id" => $id,
            "nrc" => $NRC
        );

        $came = 0;
        $notcame = 0;
        $arrivedlate = 0;
        $leftsoon = 0;
        $DK = 0;

        foreach($data as $value){

            $attendance_log = $value["ATTENDANCE"];

            switch ($attendance_log) {
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

        //Two dimensional arrays
        if($total!=0) {
            $attendance = array(
                array("key" => "Came", "value" => round($came * 100 / $total, 2, PHP_ROUND_HALF_UP)), //2 decimals round
                array("key" => "Did not come", "value" => round($notcame * 100 / $total, 2, PHP_ROUND_HALF_UP)),
                array("key" => "Arrived late", "value" => round($arrivedlate * 100 / $total, 2, PHP_ROUND_HALF_UP)),
                array("key" => "Left Soon", "value" => round($leftsoon * 100 / $total, 2, PHP_ROUND_HALF_UP)),
                array("key" => "Undefined", "value" => round($DK * 100 / $total, 2, PHP_ROUND_HALF_UP))
            );
        }else{
            $attendance = array(
                array("key" => "Came", "value" => 0),
                array("key" => "Did not come", "value" => 0),
                array("key" => "Arrived late", "value" => 0),
                array("key" => "Left Soon", "value" => 0),
                array("key" => "Undefined", "value" => 100)
            );
        }

        $object["attendance"] = $attendance;

        //$jsonp = json_encode($object);

        if($response) {
            return response()->json($object); //returns json object
        }else{
            return($object); //returns an array
        }
    }
/*
    public function showStatisticsByStudent($id)
    {
        $response_data = array(
            "student_id" => $id,
            "resource_uri" => "/student/".$id,
            "courses" => array()
        );

        $courses = CoursesByStudentModel::where("STUDENTID", "=", $id)->get()->toArray();

        foreach($courses as $course){
            $student_attendance = self::showStatisticsByStudentByCourse($id, $course["NRC"], false);
            $course_attendance = array(
                "subject_name" => $course["SUBJECTNAME"],
                "nrc" => $course["NRC"],
                "resource_uri" => "/course/".$course["NRC"],
                //"attendance" => $student_attendance
                "attendance" => array(
                    "came" => $student_attendance["came"],
                    "did_not_come" => $student_attendance["did_not_come"],
                    "arrived_late" => $student_attendance["arrived_late"],
                    "left_soon" => $student_attendance["left_soon"],
                    "undefined" => $student_attendance["undefined"]
                )
            );
            $response_data["courses"][] = $course_attendance;
        }

        return response()->json($response_data);

    }*/

    public function showStatisticsByCourse($NRC)
    {
        $response_data = array(
            "nrc" => $NRC,
            "resource_uri" => "/course/".$NRC,
            "students" => array()
        );

        $students = StudentsByCourseModel::where("NRC", "=", $NRC)->get()->toArray();

        foreach($students as $student){
            $course_attendance = self::showStatisticsByStudentByCourse($student["STUDENTID"], $NRC, false);
            // var_dump($course_attendance);

            $student_attendance = array(
                "student_name" => $student["NAMES"],
                "student_lastname" => $student["LASTNAMES"],
                "student_id" => $student["STUDENTID"],
                "resource_uri" => "/student/".$student["STUDENTID"]."/attendance",

                "attendance" => array(
                    array("key" => "Came", "value" => $course_attendance["attendance"][0]["value"]),
                    array("key" => "Did not come", "value" => $course_attendance["attendance"][1]["value"]),
                    array("key" => "Arrived late", "value" => $course_attendance["attendance"][2]["value"]),
                    array("key" => "Left Soon", "value" => $course_attendance["attendance"][3]["value"]),
                    array("key" => "Undefined", "value" => $course_attendance["attendance"][4]["value"])
                )
            );
            $response_data["students"][] = $student_attendance;
        }

        return response()->json($response_data);

    }

    /*public function showStatisticsByStudent($id)
    {
        $response_data = array(
            "student_id" => $id,
            "resource_uri" => "/student/".$id,
            "courses" => array()
        );

        $courses = CoursesByStudentModel::where("STUDENTID", "=", $id)->get()->toArray();

        foreach($courses as $course){
            $student_attendance = self::showStatisticsByStudentByCourse($id, $course["NRC"], false);
            $course_attendance = array(
                "subject_name" => $course["SUBJECTNAME"],
                "nrc" => $course["NRC"],
                "resource_uri" => "/course/".$course["NRC"],
                //"attendance" => $student_attendance
                "attendance" => array(
                    "came" => $student_attendance["came"],
                    "did_not_come" => $student_attendance["did_not_come"],
                    "arrived_late" => $student_attendance["arrived_late"],
                    "left_soon" => $student_attendance["left_soon"],
                    "undefined" => $student_attendance["undefined"]
                )
            );
            $response_data["courses"][] = $course_attendance;
        }

        return response()->json($response_data);

    }*/

}