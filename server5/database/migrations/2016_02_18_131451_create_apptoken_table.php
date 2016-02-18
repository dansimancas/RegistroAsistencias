<?php

use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateApptokenTable extends Migration {

    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up() {
        Schema::create('apptoken', function(Blueprint $table) {
            $table->increments('ID')->unsigned();
            $table->string('USERNAME', 10);
            $table->string('TOKEN', 50);
            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down() {
        Schema::drop('apptoken');
    }

}
