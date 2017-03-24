<?php

use yii\helpers\Html;
use yii\grid\GridView;

/* @var $this yii\web\View */
/* @var $searchModel common\models\UserdataSearch */
/* @var $dataProvider yii\data\ActiveDataProvider */

$this->title = 'Userdatas';
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="userdata-index">

    <h1><?= Html::encode($this->title) ?></h1>
    <?php // echo $this->render('_search', ['model' => $searchModel]); ?>

    <p>
        <?= Html::a('Create Userdata', ['create'], ['class' => 'btn btn-success']) ?>
    </p>
    <?= GridView::widget([
        'dataProvider' => $dataProvider,
        'filterModel' => $searchModel,
        'columns' => [
            ['class' => 'yii\grid\SerialColumn'],

            'id',
            'user_id',
            'first_name',
            'last_name',
            'allowed_liters',
            // 'consumed_liters',

            ['class' => 'yii\grid\ActionColumn'],
        ],
    ]); ?>
</div>
