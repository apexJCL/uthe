<?php

use yii\db\Migration;

/**
 * Handles the creation of table `user_intakes`.
 */
class m170324_200628_create_user_intakes_table extends Migration
{
    /**
     * @inheritdoc
     */
    public function up()
    {
        $this->createTable('user_intakes', [
            'id' => $this->primaryKey(),
            'user_id' => $this->integer()->notNull(),
            'intake_id' => $this->integer()->notNull(),
            'time' => $this->integer(),
            'volume' => $this->integer()
        ]);
        $this->addForeignKey('user_intake_user_fk', 'user_intakes', 'user_id', 'user', 'id');
        $this->addForeignKey('user_intake_intake_fk', 'user_intakes', 'intake_id', 'intake', 'id');
    }

    /**
     * @inheritdoc
     */
    public function down()
    {
        $this->dropTable('user_intakes');
    }
}
