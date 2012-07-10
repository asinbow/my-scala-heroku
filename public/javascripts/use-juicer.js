/*
 * fromJuicerTemplate(node, data, templateNode, clearnode)
 * fromJuicerTemplate(node, data, clearnode); // templateNode default to node + 'Template'
 * */
function fromJuicerTemplate(node, data, templateNode, clearnode) {
    if (typeof(templateNode)=='boolean') {
        clearnode = templateNode;
        templateNode = node + 'Template';
    } else if (templateNode==undefined) {
        templateNode = node + 'Template';
    }
    if (clearnode==undefined) {
        clearnode = true;
    }
    var nodeEle = $(node);
    if (clearnode) {
        nodeEle.text("");
        nodeEle.children().remove();
    }
    var html;
    if (data) {
        var templateEle = $(templateNode);
        var template;
        if (templateEle.length>0) {
            template = templateEle.text();
            html = juicer(template, {data:data});
        } else {
            console.error("template node " + templateNode + "missing!");
        }
    } else {
        html = templateNode;
    }
    if (html) {
        nodeEle.append(html);
    }
}
