<?php namespace App;

use Illuminate\Database\Eloquent\Model;

class SiriusCourseModel extends Model {
	
	//Se asigna la tabla de cursos para trabajar con el SiriusCoursesModel. En este caso la tabla de cursos
    // que contiene información sobre los cursos: el NRC, periodo, materia, curso, sección, nombre de la asignatura
    // nombre del profesor, número de créditos  y horas semanales.
    protected $table = 'SIRIUS_COURSES';

}
