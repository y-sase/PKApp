package com.example.XXX

import android.graphics.Color
import android.graphics.Typeface
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.RadarChart
import com.github.mikephil.charting.data.RadarData
import com.github.mikephil.charting.data.RadarDataSet
import com.github.mikephil.charting.data.RadarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter


@Composable
fun RadarGraph(resultMap: Map<String, Float>) {
    AndroidView(
        //Composeの中で普通のAndroid Viewを表示する仕組み
        factory = { context ->

            val radarChart = RadarChart(context)

            radarChart.isRotationEnabled = false

            val entries = mutableListOf<RadarEntry>()//グラフの値を入れるリスト
            val labels = mutableListOf<String>()//HP、攻撃、防御などのラベルを入れるリスト
            for ((k, v) in resultMap) {//resultMapを1個ずつ取り出す
                entries.add(RadarEntry(v))//グラフのデータ追加
                labels.add(k)
            }

            val dataSet = RadarDataSet(entries, "").apply {//entriesからデータセット作成
                color = Color.rgb(0,188,212)
                lineWidth = 3f
                //setDrawHighlightCircleEnabled(true) //頂点の丸
                setDrawFilled(true)      // 塗りつぶしON
                fillColor = Color.rgb(178,235,242)   // 塗りつぶし色
                setDrawValues(false) // 値を表示
               // valueTextSize = 12f
               // valueTextColor = Color.BLACK
            }

            val xAxis = radarChart.xAxis//レーダーチャートの外周の軸を取得
            xAxis.valueFormatter = IndexAxisValueFormatter(
                labels
            )
            val maxStat = resultMap.values.maxOrNull() ?: 100f
            radarChart.yAxis.apply {
                axisMinimum = 0f
                axisMaximum = maxStat * 1.2f
                labelCount = 5
            }
            //軸・枠線・メモリ
            radarChart.webColor = Color.LTGRAY
            radarChart.webColorInner = Color.GRAY
            radarChart.webLineWidth = 2f
            radarChart.webLineWidthInner = 1f

            //ラベル文字
            radarChart.xAxis.textSize = 15f
            radarChart.xAxis.textColor = Color.rgb(0,131,143)
            radarChart.xAxis.typeface = Typeface.DEFAULT_BOLD
            radarChart.yAxis.setDrawLabels(false)

            val l = radarChart.legend//凡例取得
            l.isEnabled = false//凡例非表示

            val desc = radarChart.description//右下のDescription取得
            desc.isEnabled = false//Description非表示
            val radarData = RadarData(dataSet)//データセットから実際のグラフデータ作成
            radarChart.data = radarData//チャートにデータセット
            radarChart.invalidate()//再描画
            radarChart//factoryの最後は表示するViewを返す
        },

        modifier = Modifier
            .height(500.dp)
            .width(700.dp),


    )
}