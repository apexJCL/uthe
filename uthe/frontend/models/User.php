<?php
/**
 * Created by IntelliJ IDEA.
 * User: apex
 * Date: 24/03/17
 * Time: 01:29 PM
 */

namespace frontend\models;

use common\models\User as BaseUser;
use common\models\Userdata;

class User extends BaseUser
{

    /**
     * Regusters a new user
     *
     * @param $rawBody
     * @return array
     */
    public static function register($rawBody)
    {
        $u = json_decode($rawBody);

        if (!isset($u) || empty($u))
            return [
                'has_errors' => true,
                'errors' => "No user data has been received"
            ];

        if (!isset($u->userdata))
            return [
                'has_errors' => true,
                'errors' => 'No user infomation available (userdata entity)'
            ];

        $user = new BaseUser();
        $user->username = $u->username;
        $user->email = $u->email;
        $user->setPassword($u->password);
        $user->generateAuthKey();
        if (!$user->validate()) {
            return [
                'has_errors' => true,
                'errors' => 'There has been errors during data validation ' . var_dump($user->errors)
            ];
        }
        $user->save();
        Userdata::newUser($u->userdata, $user->id);
        return [
            'user_id' => $user->id,
            'has_errors' => false,
            'errors' => ''
        ];
    }

    public function fields()
    {
        return [
            'id',
            'username',
            'email'
        ];
    }

    public function rules()
    {
        return [
            [['username', 'email', 'password'], 'required']
        ];
    }

}