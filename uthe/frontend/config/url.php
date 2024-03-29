<?php
/**
 * Created by IntelliJ IDEA.
 * User: apex
 * Date: 24/03/17
 * Time: 01:05 PM
 */

return [
    [
        'pattern' => '/user/<user_id>/statistics',
        'route' => 'user/statistics',
        'encodeParams' => false
    ],
    [
        'pattern' => '/control/enable/<user_id>/<intake_id>',
        'route' => 'control/enable',
        'encodeParams' => false
    ],
    [
        'class' => \yii\rest\UrlRule::className(),
        'controller' => 'intake'
    ]
];