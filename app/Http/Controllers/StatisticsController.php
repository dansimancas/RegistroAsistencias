<?php

/**
 * Created by PhpStorm.
 * User: Laura Schiatti
 * Date: 25/03/2015
 * Time: 10:12 PM
 */

namespace App\Http\Controllers;

use App\AttendanceModel;
use App\MatriculasModel;
use App\StudentsModel;
use App\CoursesModel;

class StatisticsController extends Controller {

    /**
     * Función para mostrar estadisticas de asistencia de un estudiante a un curso
     * @param $idstudent, $idcourse
     * @return \Symfony\Component\HttpFoundation\Response
     */
    public function showStatisticsByStudentByCourse($id, $NRC, $response = true) {
        $data = AttendanceModel::where("STUDENTID", "=", $id)
                ->where("NRC", "=", $NRC)->get();
        if ($data->isEmpty()) {
            $student = StudentsModel::where("ID", "=", $id)->first();
            if (!$student) {
                $object = "No existe un estudiante con el código " . $id;
                return $object;
            }else{
                $course = CoursesModel::where("NRC_PERIODO_KEY", "=", $NRC)->first();
                if (!$course) {
                    $object = "No existe un curso con el NRC " . $NRC;
                    return $object;
                }
            }
        }
        $object = array("student_id" => $id,"nrc" => $NRC);
        $came = $notcame = 0;
        foreach ($data as $value) {
            $attendance_log = $value["ATTENDANCE"];
            switch ($attendance_log) {
                case 0:
                    $came += 1;
                    break;
                case 1:
                    $notcame += 1;
                    break;
            }
        }
        $total = $came + $notcame;
        $attendance = array();
        //Two dimensional arrays
        if ($total != 0) {
            $attendance['percent'] = array(
                array("key" => "Came", "value" => round($came * 100 / $total, 2, PHP_ROUND_HALF_UP)), //2 decimals round
                array("key" => "Did not come", "value" => round($notcame * 100 / $total, 2, PHP_ROUND_HALF_UP)),
            );
            $attendance['value'] =  array(
                array("key" => "Came", "value" => $came), //2 decimals round
                array("key" => "Did not come", "value" => $notcame),
            );
        } else {
            $attendance['percent'] = array(
                array("key" => "Came", "value" => 0),
                array("key" => "Did not come", "value" => 0),
            );
            $attendance['value'] = $attendance['percent'];
        }
        $object["attendance"] = $attendance;

        if ($response) {
            return response()->json($object); //returns json object
        } else {
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

      } */

    public function showStatisticsByCourse($NRC) {
        $response_data = array(
            "nrc" => $NRC,
            "resource_uri" => "/course/" . $NRC,
            "students" => array()
        );

        $matriculas = MatriculasModel::where("IDNUMBER", "=", $NRC)->where('ROLE','=', 'student')->get();

        foreach ($matriculas as $matricula) {
            $student = $matricula->student;
            $course_attendance = self::showStatisticsByStudentByCourse($student["ID"], $NRC, false);
            $student_attendance = array(
                "student_name" => $student["NOMBRES"],
                "student_lastname" => $student["APELLIDOS"],
                "student_id" => $student["ID"],
                "resource_uri" => "/student/" . $student["STUDENTID"] . "/attendance",
                "attendance_percent" => array(
                    array("key" => "Came", "value" => $course_attendance["attendance"]["percent"][0]["value"]),
                    array("key" => "Did not come", "value" => $course_attendance["attendance"]["percent"][1]["value"]),
                ),
                "attendance_value" => array(
                    array("key" => "Came", "value" => $course_attendance["attendance"]["value"][0]["value"]),
                    array("key" => "Did not come", "value" => $course_attendance["attendance"]["value"][1]["value"]),
                )
            );
            $response_data["students"][] = $student_attendance;
        }

        return response()->json($response_data);
    }

    /* public function showStatisticsByStudent($id)
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

      } */
}
