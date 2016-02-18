<?php namespace App;

use Illuminate\Database\Eloquent\Model;

class CoursesByTeacherModel extends Model {

    /**
     * Se asigna la vista de CoursesByTeacher existente en la base de datos. Esta vista contiene la siguiente información:
     * TEACHERID (código del PROFESOR), SUBJECTNAME (nombre de la materia), NRC (código de la materia), SECTION (sección),
     * NAMES, LASTNAMES (nombres y apellidos del profesor, respectivamente).
     * @var string
     */
    protected $table = 'coursesbyteacher';

}
