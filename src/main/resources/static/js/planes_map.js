 d3.select(window)
        .on("mousemove", mousemove)
        .on("mouseup", mouseup);

var width = document.documentElement.clientWidth,
    height = document.documentElement.clientHeight;

// Задаём иатрицу проекции
var proj = d3.geo.orthographic()
    .translate([width / 2, height / 2])
    .clipAngle(90)  // угол обзора
    .scale(height / 2.5);    // размер планеты

var sky = d3.geo.orthographic()
    .translate([width / 2, height / 2])
    .clipAngle(90)  // угол обзора
    .scale(height / 2.5 + height/10);    // высота неба

var path = d3.geo.path().projection(proj).pointRadius(5);

var swoosh = d3.svg.line() // рисует по входящим данным парболу
    .x(function(d) { return d[0] })
    .y(function(d) { return d[1] })
    .interpolate("cardinal")    // метод отрисовки
    .tension(.0);               // сглаживание 0.0 => парабола; 1.0 => угол

var links = [],
    arcLines = [],
    dots = [];

var svg = d3.select("body").append("svg")   // добавляем к body svg
    .attr("width", width)                   // задаём ширину
    .attr("height", height)                 // задаём высоту
    .on("mousedown", mousedown);            // подключаем обработчик нажатия мыши

queue() // грузим данные
    .defer(d3.json, "https://gist.githubusercontent.com/mbostock/4090846/raw/07e73f3c2d21558489604a0bc434b3a5cf41a867/world-110m.json")
    .defer(d3.json, "../json/places.json")
    .await(ready);

function ready(error, world, places) {

    var ocean_fill = svg.append("defs").append("radialGradient")    // Задаёс груговой градиент
        .attr("id", "ocean_fill")                                   // Присваиваем ID
        .attr("cx", "75%")                                          // Задаём позиции
        .attr("cy", "25%");                                         // Задаём позиции
    ocean_fill.append("stop").attr("offset", "5%").attr("stop-color", "#fff");      // Устанавливаем фоновый свет (блик)
    ocean_fill.append("stop").attr("offset", "100%").attr("stop-color", "#ababab"); // Устанавливаем основной цвет моря

    // задаём глобальное освещение
    var globe_highlight = svg.append("defs").append("radialGradient")
        .attr("id", "globe_highlight")
        .attr("cx", "75%")
        .attr("cy", "25%");
    globe_highlight.append("stop")
        .attr("offset", "5%").attr("stop-color", "#ffd")
        .attr("stop-opacity","0.6");
    globe_highlight.append("stop")
        .attr("offset", "100%").attr("stop-color", "#ba9")
        .attr("stop-opacity","0.2");

    // задаём глобальное освещение рисуем псевдо тени
    var globe_shading = svg.append("defs").append("radialGradient")
        .attr("id", "globe_shading")
        .attr("cx", "55%")
        .attr("cy", "45%");
    globe_shading.append("stop")
        .attr("offset","30%").attr("stop-color", "#fff")
        .attr("stop-opacity","0")
    globe_shading.append("stop")
        .attr("offset","100%").attr("stop-color", "#505962")
        .attr("stop-opacity","0.3")

    // океан
    svg.append("circle")
        .attr("cx", width / 2).attr("cy", height / 2)
        .attr("r", proj.scale())
        .attr("class", "noclicks")
        .style("fill", "url(#ocean_fill)");

    // Земля
    svg.append("path")
        .datum(topojson.object(world, world.objects.land))
        .attr("class", "land noclicks")
        .attr("d", path);

    // Глобальное освещение
    svg.append("circle")
        .attr("cx", width / 2).attr("cy", height / 2)
        .attr("r", proj.scale())
        .attr("class","noclicks")
        .style("fill", "url(#globe_highlight)");
    // Глобальное освещение тени
    svg.append("circle")
        .attr("cx", width / 2).attr("cy", height / 2)
        .attr("r", proj.scale())
        .attr("class","noclicks")
        .style("fill", "url(#globe_shading)");


    // spawn links between cities as source/target coord pairs
    places.features.forEach(function(a) {
        links.push({
            source: a.geometry.coordinates,
            target: a.geometry.coordinatesto,
            count: a.geometry.count
        });

        dots.push({ "type": "Feature", "geometry": { "type": "Point", "coordinates": a.geometry.coordinates }});
        dots.push({ "type": "Feature", "geometry": { "type": "Point", "coordinates": a.geometry.coordinatesto }});
    });

    // build geoJSON features from links array
    links.forEach(function(e) {
        var feature =   { "type": "Feature", "geometry": { "type": "LineString", "coordinates": [e.source, e.target] }}
        arcLines.push(feature)
    })

    // Тень от линий
    svg.append("g").attr("class","arcs")
        .selectAll("path").data(arcLines)
        .enter().append("path")
        .attr("class","arc")
        .attr("d",path)

    // Линии перелётов
    svg.append("g").attr("class","flyers")
        .selectAll("path").data(links)
        .enter().append("path")
        .attr("class","flyer")
        .attr("d", function(d) { return swoosh(flying_arc(d)) })
        .attr("stroke", function(d) { return color(d) })
    refresh();

    // Цвет точки
    svg.append("g")
        .attr("class","points")
        .selectAll("text").data(dots)
        .enter().append("path")
        .attr("class", "point")
        .attr("d", path);
}

function color(pts) {
    var step_count = 10;

    var r = 20 + (80 / step_count) * pts.count;
    var g = 80 - (80 / step_count) * pts.count;
    return "rgb(" + r + ","+ g + ",40)"
}

function flying_arc(pts) {
    var source = pts.source,
        target = pts.target;

    var mid = location_along_arc(source, target, .5);
    var result = [ proj(source),
        sky(mid),
        proj(target) ]
    return result;
}

function refresh() {
    svg.selectAll(".land").attr("d", path);
    svg.selectAll(".point").attr("d", path);

    svg.selectAll(".arc").attr("d", path)
        .attr("opacity", function(d) {
            return fade_at_edge(d)
        })

    svg.selectAll(".flyer")
        .attr("d", function(d) { return swoosh(flying_arc(d)) })
        .attr("opacity", function(d) { return fade_at_edge(d) })
        .attr("stroke-width", function(d) { return d.count > 5 ? d.count - 2 : 2})
}

function fade_at_edge(d) {
    var centerPos = proj.invert([width/2, height/2]),
        arc = d3.geo.greatArc(),
        start, end;

    // function is called on 2 different data structures..
    if (d.source) {
        start = d.source, end = d.target;
    }
    else {
        start = d.geometry.coordinates[0];
        end = d.geometry.coordinates[1];
    }

    var start_dist = 1.57 - arc.distance({source: start, target: centerPos}),
        end_dist = 1.57 - arc.distance({source: end, target: centerPos});

    var fade = d3.scale.linear().domain([-.1,0]).range([0,.1])
    var dist = start_dist < end_dist ? start_dist : end_dist;

    return fade(dist)
}

function location_along_arc(start, end, loc) {
    var interpolator = d3.geo.interpolate(start,end);
    return interpolator(loc)
}

// modified from http://bl.ocks.org/1392560
var m0, o0;
function mousedown() {
    m0 = [d3.event.pageX, d3.event.pageY];
    o0 = proj.rotate();
    d3.event.preventDefault();
}

function mousemove() {
    if (m0) {
        var m1 = [d3.event.pageX, d3.event.pageY]
            , o1 = [o0[0] + (m1[0] - m0[0]) / 6, o0[1] + (m0[1] - m1[1]) / 6];
        o1[1] = o1[1] > 30  ? 30  :
            o1[1] < -30 ? -30 :
                o1[1];
        proj.rotate(o1);
        sky.rotate(o1);
        refresh();
    }
}

function mouseup() {
    if (m0) {
        mousemove();
        m0 = null;
    }
}