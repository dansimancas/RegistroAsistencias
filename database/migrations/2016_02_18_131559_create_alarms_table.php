<?php

use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateAlarmsTable extends Migration {

    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up() {
        Schema::create('alarms', function(Blueprint $table) {
            $table->increments('ID')->unsigned();
            $table->string('TEACHER', 10);
            $table->string('COURSE', 15);
            $table->string('TYPE', 10);
            $table->string('MESSAGE', 130);
            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down() {
        Schema::drop('alarms');
    }

}
