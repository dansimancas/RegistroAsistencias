<?php namespace App;

use Illuminate\Database\Eloquent\Model;

class StudentsByCourseModel extends Model {

	/*
	 * Carga los datos de estudiantes y profesores y los relaciona por los NRC activos mediante MRC_PERIODO_KEY
	 * */
    protected $table = 'StudentsByCourse';

}
