<?php
/**
 * Created by IntelliJ IDEA.
 * User: apex
 * Date: 24/03/17
 * Time: 06:18 PM
 */

namespace frontend\controllers;


use common\models\LoginForm;
use frontend\models\Statistics;
use frontend\models\User;
use yii\filters\VerbFilter;
use yii\web\Controller;
use yii\web\Response;

class UserController extends Controller
{

    public $enableCsrfValidation = false;

    public function behaviors()
    {
        return [
            'verbs' => [
                'class' => VerbFilter::className(),
                'actions' => [
                    'login' => ['POST'],
                    'statistics' => ['GET'],
                    'signup' => ['POST']
                ]
            ]
        ];
    }

    public function actionSignup()
    {
        \Yii::$app->response->format = Response::FORMAT_JSON;
        return User::register(\Yii::$app->request->rawBody);
    }

    /**
     * Login a user
     */
    public function actionLogin()
    {
        \Yii::$app->response->format = Response::FORMAT_JSON;
        $model = LoginForm::newFromJSON(\Yii::$app->request->getRawBody());
        return $model->login();
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