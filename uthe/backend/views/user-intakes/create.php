<?php

use yii\helpers\Html;


/* @var $this yii\web\View */
/* @var $model common\models\UserIntakes */

$this->title = 'Create User Intakes';
$this->params['breadcrumbs'][] = ['label' => 'User Intakes', 'url' => ['index']];
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="user-intakes-create">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
