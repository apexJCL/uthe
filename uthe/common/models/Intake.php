<?php

namespace common\models;

use Yii;

/**
 * This is the model class for table "intake".
 *
 * @property integer $id
 * @property string $description
 * @property integer $time
 * @property integer $volume
 *
 * @property UserIntakes[] $userIntakes
 */
class Intake extends \yii\db\ActiveRecord
{
    /**
     * @inheritdoc
     */
    public static function tableName()
    {
        return 'intake';
    }

    /**
     * @inheritdoc
     */
    public function rules()
    {
        return [
            [['description', 'time', 'volume'], 'required'],
            [['time', 'volume'], 'integer'],
            [['description'], 'string', 'max' => 100],
        ];
    }

    /**
     * @inheritdoc
     */
    public function attributeLabels()
    {
        return [
            'id' => 'ID',
            'description' => 'Description',
            'time' => 'Time',
            'volume' => 'Volume',
        ];
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getUserIntakes()
    {
        return $this->hasMany(UserIntakes::className(), ['intake_id' => 'id']);
    }
}
