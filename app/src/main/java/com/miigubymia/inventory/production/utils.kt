package com.miigubymia.inventory.production

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.io.File
import java.io.FileWriter

fun writeTextInInternalStorage(context: Context, activity: Activity, content: String) {
    val internalDir = activity.applicationContext.filesDir
    val file = File(internalDir, "registro_producao.txt")
    try {
        if (!file.exists()) {
            file.createNewFile()
        }
        val writer = FileWriter(file, true)
        writer.write(content)
        writer.close()
        Toast.makeText(context, "Salvo.", Toast.LENGTH_SHORT).show()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun writeToExternalStorage(context: Context, activity: Activity, content:String) {
    if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE)
        != PackageManager.PERMISSION_GRANTED
    ) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Permissão Necessária")
        builder.setMessage("Para que o aplicativo possa salvar o registro no cartão SD, você precisa liberar a permissão de acesso aos arquivos. Caso contrário o arquivo será salvo apenas na memória interna.")
        builder.setPositiveButton("Conceder permissão") { dialog, which ->
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE), 100)
        }
        builder.setNegativeButton("Cancelar") { dialog, which ->
            Toast.makeText(context, "Permissão NÃO concedida", Toast.LENGTH_SHORT).show()
        }
        builder.show()
    }else{
        val externalDir = activity.applicationContext.getExternalFilesDir(null)
        val file = File(externalDir, "registro_producao.csv")
        try {
            if (!file.exists()) {
                file.createNewFile()
            }
            val writer = FileWriter(file, true)
            writer.use { it.write(content)}
            Toast.makeText(context, "Salvo.", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

fun createList(list:MutableList<String>, productionAdapter:ProductionAdapter){
    list.clear()
    list.add("ID da Produção,Data de Entrega,Nome do Artesao,Nome do Produto,Quantidade")
    val productionList = productionAdapter.production
    var loop = 0
    for (item in productionList){
        var id = productionList[loop].productionID.toString()
        var date = productionList[loop].date
        var artisanName = productionList[loop].artisanName
        var productName = productionList[loop].productName
        var quantity = productionList[loop].productionQuantity.toString()
        var line = "$id,$date,$artisanName,$productName,$quantity"
        list.add(line)
        loop += 1
    }
}
