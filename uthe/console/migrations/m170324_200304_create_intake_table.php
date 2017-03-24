<?php

use yii\db\Migration;

/**
 * Handles the creation of table `intake`.
 */
class m170324_200304_create_intake_table extends Migration
{
    /**
     * @inheritdoc
     */
    public function up()
    {
        $this->createTable('intake', [
            'id' => $this->primaryKey(),
            'description' => $this->string(100)->notNull(),
            'time' => $this->integer()->notNull(),
            'volume' => $this->integer()->notNull()
        ]);
    }

    /**
     * @inheritdoc
     */
    public function down()
    {
        $this->dropTable('intake');
    }
}
