<?php
    include 'connexion.php';
    $pg= pg_query("select * from prueba");
    $arr2 = pg_fetch_all_columns($pg, 1);
    echo $arr2[0];
?>
        
