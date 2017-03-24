<?php
/**
 * Created by IntelliJ IDEA.
 * User: apex
 * Date: 24/03/17
 * Time: 01:29 PM
 */

namespace frontend\models;

use common\models\User as BaseUser;

class User extends BaseUser
{
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