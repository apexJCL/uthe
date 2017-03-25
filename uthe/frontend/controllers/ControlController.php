<?php
/**
 * Created by IntelliJ IDEA.
 * User: apex
 * Date: 24/03/17
 * Time: 06:09 PM
 */

namespace frontend\controllers;

use yii\filters\VerbFilter;
use yii\web\Controller;
use yii\web\Response;

class ControlController extends Controller
{

    public $enableCsrfValidation = false;

    public function behaviors()
    {
        return [
            'verbs' => [
                'class' => VerbFilter::className(),
                'actions' => [
                    'enable' => ['GET']
                ]
            ]
        ];
    }

    /*
     * Enables an intake
     */
    public function actionEnable($user_id, $intake_id)
    {
        \Yii::$app->response->format = Response::FORMAT_JSON;
        return "Ok";
    }


}