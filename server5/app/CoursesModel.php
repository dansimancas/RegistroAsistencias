<?php namespace App;

use Illuminate\Database\Eloquent\Model;

class CoursesModel extends Model {

    /**
     * Se asigna la tabla de Sirius_Courses existente en la base de datos. Esta tabla contiene la siguiente información:
     * NRC_PERIODOD_KEY(código de un materia, año y periodo), NRC (código de la materia), PERIODO(periodo de la materia),
     * MATERIA(facultad que dicta la materia), CURSO(código del curso), SECCIÓN (sección), NOMBREASIGNATURA(nombre de la materia), DOCENTEID(id del docente que dicta la materia),
     * CRÉDITOS(créditos de la materia), HORAS_SEMANALAES(cantidad de horas que se dicta la materia semanalmente).
     * @var string
     */
    protected $table = 'SIRIUS_COURSES';
    protected $connection = 'sirius_bd';

}
