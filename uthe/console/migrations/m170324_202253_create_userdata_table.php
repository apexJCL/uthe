<?php

use yii\db\Migration;

/**
 * Handles the creation of table `userdata`.
 */
class m170324_202253_create_userdata_table extends Migration
{
    /**
     * @inheritdoc
     */
    public function up()
    {
        $this->createTable('userdata', [
            'id' => $this->primaryKey(),
            'user_id' => $this->integer()->notNull(),
            'first_name' => $this->string(100)->notNull(),
            'last_name' => $this->string(100)->notNull(),
            'allowed_liters' => $this->integer()->notNull()->defaultValue(300),
            'consumed_liters' => $this->integer()->notNull()->defaultValue(0)
        ]);
        $this->addForeignKey('userdata_user_fk', 'userdata', 'user_id', 'user', 'id');
    }

    /**
     * @inheritdoc
     */
    public function down()
    {
        $this->dropTable('userdata');
    }
}
