<?php

use yii\helpers\Html;
use yii\grid\GridView;

/* @var $this yii\web\View */
/* @var $searchModel common\models\UserIntakesSearch */
/* @var $dataProvider yii\data\ActiveDataProvider */

$this->title = 'User Intakes';
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="user-intakes-index">

    <h1><?= Html::encode($this->title) ?></h1>
    <?php // echo $this->render('_search', ['model' => $searchModel]); ?>

    <p>
        <?= Html::a('Create User Intakes', ['create'], ['class' => 'btn btn-success']) ?>
    </p>
    <?= GridView::widget([
        'dataProvider' => $dataProvider,
        'filterModel' => $searchModel,
        'columns' => [
            ['class' => 'yii\grid\SerialColumn'],

            'id',
            'user_id',
            'intake_id',
            'time',
            'volume',

            ['class' => 'yii\grid\ActionColumn'],
        ],
    ]); ?>
</div>
