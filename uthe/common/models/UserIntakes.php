<?php

namespace common\models;

use Yii;

/**
 * This is the model class for table "user_intakes".
 *
 * @property integer $id
 * @property integer $user_id
 * @property integer $intake_id
 * @property integer $time
 * @property integer $volume
 *
 * @property Intake $intake
 * @property User $user
 */
class UserIntakes extends \yii\db\ActiveRecord
{
    /**
     * @inheritdoc
     */
    public static function tableName()
    {
        return 'user_intakes';
    }

    /**
     * @inheritdoc
     */
    public function rules()
    {
        return [
            [['user_id', 'intake_id'], 'required'],
            [['user_id', 'intake_id', 'time', 'volume'], 'integer'],
            [['intake_id'], 'exist', 'skipOnError' => true, 'targetClass' => Intake::className(), 'targetAttribute' => ['intake_id' => 'id']],
            [['user_id'], 'exist', 'skipOnError' => true, 'targetClass' => User::className(), 'targetAttribute' => ['user_id' => 'id']],
        ];
    }

    /**
     * @inheritdoc
     */
    public function attributeLabels()
    {
        return [
            'id' => 'ID',
            'user_id' => 'User ID',
            'intake_id' => 'Intake ID',
            'time' => 'Time',
            'volume' => 'Volume',
        ];
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getIntake()
    {
        return $this->hasOne(Intake::className(), ['id' => 'intake_id']);
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getUser()
    {
        return $this->hasOne(User::className(), ['id' => 'user_id']);
    }
}
