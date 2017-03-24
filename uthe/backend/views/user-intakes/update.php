<?php

use yii\helpers\Html;

/* @var $this yii\web\View */
/* @var $model common\models\UserIntakes */

$this->title = 'Update User Intakes: ' . $model->id;
$this->params['breadcrumbs'][] = ['label' => 'User Intakes', 'url' => ['index']];
$this->params['breadcrumbs'][] = ['label' => $model->id, 'url' => ['view', 'id' => $model->id]];
$this->params['breadcrumbs'][] = 'Update';
?>
<div class="user-intakes-update">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
