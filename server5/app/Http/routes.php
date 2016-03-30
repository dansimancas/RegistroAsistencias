<?php

/*
|--------------------------------------------------------------------------
| Application Routes
|--------------------------------------------------------------------------
|
| Here is where you can register all of the routes for an application.
| It's a breeze. Simply tell Laravel the URIs it should respond to
| and give it the controller to call when that URI is requested.
|
*/

use App\LogsModel;
use App\Http\Middleware\TokenAuthMiddleware;
use App\Http\Controllers\PersonsController;
use App\Http\Controllers\CoursesController;
use App\Http\Controllers\StatisticsController;
use App\Http\Controllers\AttendanceController;
use App\Http\Controllers\AlarmsController;

Route::get('prueba', function(){
    $matricula = \App\CoursesModel::find('1425-201610');
    dd($matricula->matriculas[2]->student);
});

//Event Listeners
Event::listen('teacher.showTeachersInfo', function($id)
{
    /*$mid = new TokenAuthMiddleware();
    $user = $mid->getUsername();

    if($id == $user){
        $event = "The user ". $user." required it´s own info";
    }else{
        $event = "The user ". $user." required teacher ". $id. " info";
    }

    $logs = new LogsModel;
    $logs->EVENT= $event;
    $logs->TYPE= "teacher";
    $logs->save();*/
});

Event::listen('student.showStudentsInfo', function($id)
{
    $event = "Student ". $id. " info was required";
    $logs = new LogsModel;
    $logs->EVENT= $event;
    $logs->TYPE= "student";
    $logs->save();
});

Event::listen('course.showCoursesInfo', function($NRC)
{
    $event = "Course ". $NRC. " info was required";
    $logs = new LogsModel;
    $logs->EVENT= $event;
    $logs->TYPE= "course";
    $logs->save();
});

Event::listen('course.showCoursesByTeacher', function($id)
{
    $event = "Teacher ". $id. " courses were required";
    $logs = new LogsModel;
    $logs->EVENT= $event;
    $logs->TYPE= "course";
    $logs->save();
});


//Routes
Route::post('token', ['middleware' => 'tokengenerator', 'uses' => 'Auth\AuthController@token']);

//Route::group(['middleware' => 'tokenauth'], function() {

        /*
     * Muestra la información de un profesor
     */
    Route::get('teacher/{id}', function ($id) {
        Event::fire('teacher.showTeachersInfo', $id);
        $controller = new PersonsController();
        return $controller->showTeachersInfo($id);
    });

    /*
     * Muestra la información de un estudiante
     */
    Route::get('student/{id}', function ($id) {
        Event::fire('student.showStudentsInfo', $id);
        $controller = new PersonsController();
        return $controller->showStudentsInfo($id);
    });

    /*
     * Muestra la información de un curso
     */
    Route::get('course/{NRC}', function ($NRC) {
        Event::fire('course.showCoursesInfo', $NRC);
        $controller = new CoursesController();
        return $controller->showCoursesInfo($NRC);
    });

    /*
    * Muestra cursos que dicta el profesor id. Devuelve colección de cursos.
    */
    Route::get('teacher/{id}/courses', function ($id) {
        Event::fire('course.showCoursesByTeacher', $id);
        $controller = new CoursesController();
        return $controller->showCoursesByTeacher($id);
    });

    /*
    * Muestra la lista de estudiantes del curso NRC del profesor id ya capturado
    */
    Route::get('course/{NRC}/students', 'CoursesController@showStudentsByCourse');

    /*
     * Muestra los cursos en los que esta inscrito el estudiante id
     */
        Route::get('student/{id}/courses', 'CoursesController@showCoursesByStudent');

    /*
     * Guarda asistencias en la base de datos
     */
    Route::resource('attendance', 'AttendanceController');

    Route::group(['middleware' => 'filter'], function () {
        /*
         * Muestra las estadisticas de asistencia de un estudiante a un curso en %
         */
        Route::get('student/{id}/course/{NRC}/attendance', 'StatisticsController@showStatisticsByStudentByCourse');

        /*
         * Muestra las estadisticas de asistencia de un curso
         */
        Route::get('course/{NRC}/attendance', function ($NRC) {
            Event::fire('course.showCoursesInfo', $NRC);
            $controller = new StatisticsController();
            return $controller->showStatisticsByCourse($NRC);
        });

        /*
         * Muestra las estadisticas de asistencia de un estudiante
         */
        //Route::get('student/{id}/attendance', 'StatisticsController@showStatisticsByStudent');

        /*
         * Muestra las alarmas por falta de asistencia de estudiantes a un curso
         */
        Route::get('course/{NRC}/alarms', function ($NRC) {
            //Event::fire('course.showCoursesInfo', $NRC);
            $controller = new AlarmsController();
            return $controller->showCoursesAlarms($NRC);
        });

        /*
        * Muestra las alarmas por falta de asistencia de estudiantes a un curso
        */
        /*Route::get('course/{NRC}/student/{id}/alarms', function ($NRC, $id) {
            //Event::fire('course.showCoursesInfo', $NRC);
            $controller = new AlarmsController();
            return $controller->showAlarmsByStudentByCourse($NRC);
        });*/
    });
//});




