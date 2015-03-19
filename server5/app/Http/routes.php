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

/*
 * muestra la información de un estudiante
 *
 */
Route::get('student/{id}', 'PersonsController@showStudentsInfo');

/*
 * muestra la información de un profesor
 *
 */
Route::get('teacher/{id}', 'PersonsController@showTeachersInfo');

/*
 * muestra la información de un curso
 *
 */
Route::get('course/{NRC}', 'CoursesController@showCoursesInfo');

/*
muestra cursos que dicta el profesor id. Devuelve colección de cursos
*/
Route::get('teacher/{id}/courses', 'CoursesController@showCoursesByTeacher');

/*
muestra la lista de estudiantes del curso NRC del profesor id ya capturado
devuelve coleccción de estudiantes
*/

Route::get('course/{NRC}/students', 'CoursesController@showStudentsByCourse');


/*
 * muestra los cursos en los que esta inscrito el estudiante id
 */
Route::get('student/{id}/courses', 'CoursesController@showCoursesByStudent');


