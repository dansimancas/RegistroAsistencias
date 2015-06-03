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

Route::post('token', ['middleware' => 'tokengenerator', 'uses' => 'Auth\AuthController@token']);

/*
 * Muestra la información de un profesor
 */
Route::get('teacher/{id}', ['middleware' => 'tokenauth', 'uses' => 'PersonsController@showTeachersInfo']);

/*
 * Muestra la información de un estudiante
 */
Route::get('student/{id}', 'PersonsController@showStudentsInfo');

/*
 * Muestra la información de un curso
 */
Route::get('course/{NRC}', 'CoursesController@showCoursesInfo');

/*
 * Muestra cursos que dicta el profesor id. Devuelve colección de cursos.
*/
Route::get('teacher/{id}/courses', 'CoursesController@showCoursesByTeacher');

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

/*
 * Muestra las estadisticas de asistencia de un estudiante a un curso en %
 */
Route::get('student/{id}/course/{NRC}/attendance',['middleware' => 'filter', 'uses' => 'StatisticsController@showStatisticsByStudentByCourse']);

/*
 * Muestra las estadisticas de asistencia de un estudiante
 */
Route::get('student/{id}/attendance', 'StatisticsController@showStatisticsByStudent');

/*
 * Muestra las estadisticas de asistencia de un curso
 */
Route::get('course/{NRC}/attendance', ['middleware' => 'filter', 'uses' => 'StatisticsController@showStatisticsByCourse']);