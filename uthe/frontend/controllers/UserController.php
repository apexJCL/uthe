<?php
/**
 * Created by IntelliJ IDEA.
 * User: apex
 * Date: 24/03/17
 * Time: 06:18 PM
 */

namespace frontend\controllers;


use frontend\models\Statistics;
use yii\filters\VerbFilter;
use yii\web\Controller;
use yii\web\Response;

class UserController extends Controller
{
    public function behaviors()
    {
        return [
            'verbs' => [
                'class' => VerbFilter::className(),
                'actions' => [
                    'login' => ['POST'],
                    'statistics' => ['GET']
                ]
            ]
        ];
    }


    public function actionIndex()
    {
        return "Hell";
    }

    /**
     * Login a user
     */
    public function actionLogin()
    {

    }

    /**
     * Returns statistics for a given user
     * @param $user_id
     * @return Statistics
     */
    public function actionStatistics($user_id)
    {
        \Yii::$app->response->format = Response::FORMAT_JSON;
        return Statistics::user($user_id);
    }

}