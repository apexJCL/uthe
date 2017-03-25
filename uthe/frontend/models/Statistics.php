<?php
/**
 * Created by IntelliJ IDEA.
 * User: apex
 * Date: 24/03/17
 * Time: 06:34 PM
 */

namespace frontend\models;


use common\models\Userdata;
use yii\base\Model;

class Statistics extends Model
{

    public $username = '';
    public $first_name = '';
    public $last_name = '';
    public $allowed_liters = '';
    public $consumed_liters = '';
    public $remaining_liters = '';


    public static function user($user_id)
    {
        $s = new self();
        /**
         * @var $u Userdata
         */
        $u = Userdata::find()->where(['user_id' => $user_id])->one();
        if (empty($u))
            return $s;
        $s->username = $u->user->username;
        $s->first_name = $u->first_name;
        $s->last_name = $u->last_name;
        $s->allowed_liters = $u->allowed_liters;
        $s->consumed_liters = $u->consumed_liters;
        $s->remaining_liters = $s->allowed_liters - $s->consumed_liters;
        return $s;
    }


}