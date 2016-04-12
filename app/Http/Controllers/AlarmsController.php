<?php

namespace App\Http\Controllers;

use App\AttendanceModel;
use App\AlarmsModel;
use App\MatriculasModel;
use App\CoursesModel;
use App\StudentsModel;
use Illuminate\Database\Eloquent\Model;

class AlarmsController extends Controller
{

    /**
     * Función para mostrar estadisticas de asistencia de un estudiante a un curso
     * @param $idstudent , $idcourse
     * @return \Symfony\Component\HttpFoundation\Response
     */
    public function createAlarm($NRC)
    {
        $studentsbycourse = MatriculasModel::where("IDNUMBER", "=", $NRC)->get();

        if ($studentsbycourse->isEmpty()) {
            $object = "No hay estudiantes matriculados en el curso";
            return response()->json($object);
        }

        $students=array();

        foreach($studentsbycourse as $value){
            array_push($students,$value["USERNAME"]);
        }

        foreach ($students as $id){
            $attendances = AttendanceModel::where("NRC", "=", $NRC)->Where("STUDENTID", "=", $id)->get();

            $came = 0;
            $notcame = 0;
            /*$arrivedlate = 0;
            $leftsoon = 0;
            $DK = 0;*/

            foreach ($attendances as $value) {

                $attendance = $value["ATTENDANCE"];

                switch ($attendance) {
                    case 0:
                        $came += 1;
                        break;
                    case 1:
                        $notcame += 1;
                        break;
                    /*case 2:
                        $arrivedlate += 1;
                        break;
                    case 3:
                        $leftsoon += 1;
                        break;
                    case 4:
                        $DK += 1;
                        break;*/
                }
            }

            //$total = $came + $notcame + $arrivedlate + $leftsoon + $DK;

            $course = CoursesModel::where("NRC_PERIODO_KEY", "=", $NRC)->get();
            $student = StudentsModel::where("ID", "=", $id)->get();

            //Danger alarm
            $danger = AlarmsModel::where("STUDENT", "=", $id)->where("COURSE", "=", $NRC)->where("TYPE", "=", "danger")->first();

            if ($notcame>= 3) {
                if ($danger == null) {
                    $alarm = new AlarmsModel;
                    $alarm->TEACHER = $course[0]["DOCENTEID"];
                    $alarm->STUDENT = $id;
                    $alarm->COURSE = $NRC;
                    $alarm->TYPE = "danger";
                    $alarm->MESSAGE = "The student " . $student[0]["NOMBRES"] . " " . $student[0]["APELLIDOS"] . " didn´t come to " . $course[0]["NOMBREASIGNATURA"] . " classes " . $notcame . " times";
                    $alarm->save();
                } else {
                    AlarmsModel::where('ID', '=', $danger["ID"])->update(['MESSAGE' => "The student " . $student[0]["NOMBRES"] . " " . $student[0]["APELLIDOS"] . " didn´t come to " . $course[0]["NOMBREASIGNATURA"] . " classes " . $notcame . " times"]);
                }
            }
        }

    }

    public function showCoursesAlarms($NRC)
    {
        $danger = AlarmsModel::where("COURSE", "=", $NRC)->Where("TYPE", "=", "danger")->get();

        $object = array(
            "teacher id" => $danger[0]["TEACHER"],
            "nrc" => $danger[0]["COURSE"]
        );

        foreach($danger as $value){
            $var = array(
                "student id" => $value["STUDENT"],
                "message" => $value["MESSAGE"],
            );
            $object["alarms"]["danger"][] = $var;
        }

        return response()->json($object);
    }
}