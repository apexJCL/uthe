<?php

use yii\helpers\Html;


/* @var $this yii\web\View */
/* @var $model common\models\Intake */

$this->title = 'Create Intake';
$this->params['breadcrumbs'][] = ['label' => 'Intakes', 'url' => ['index']];
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="intake-create">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
