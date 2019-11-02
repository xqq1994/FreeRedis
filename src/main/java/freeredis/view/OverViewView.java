package freeredis.view;

import de.felixroske.jfxsupport.AbstractFxmlView;
import de.felixroske.jfxsupport.FXMLView;
import freeredis.entity.HostAndPort;
import freeredis.util.Persistant;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@FXMLView
public class OverViewView extends AbstractFxmlView {
    Logger logger = LoggerFactory.getLogger(OverViewView.class);
    public ObservableList<HostAndPort> hostAndPortsData = FXCollections.observableArrayList();

    public OverViewView() {
        //创建子节点
        final TreeItem childNode1 = new TreeItem<>("Child Node 1");
        final TreeItem childNode2 = new TreeItem<>("Child Node 2");
        final TreeItem childNode3 = new TreeItem<>("Child Node 3");

        //创建根节点
        final TreeItem root = new TreeItem<>("Root node");
        root.setExpanded(true);

        //将子节点加入到根节点中红
        root.getChildren().setAll(childNode1, childNode2, childNode3);

        //创建一个列
        TreeTableColumn<String,String> column = new TreeTableColumn<>("Column");
        column.setPrefWidth(150);

        //定义列的单元格内容
        column.setCellValueFactory((TreeTableColumn.CellDataFeatures<String, String> p) ->
                new ReadOnlyStringWrapper(p.getValue().getValue()));

        //创建一个TreeTableView
        final TreeTableView treeTableView = new TreeTableView<>(root);
        treeTableView.getColumns().add(column);
        try {
            hostAndPortsData = Persistant.readPersons();
        } catch (IOException e) {
            logger.warn("获取不到配置文件");
        }
    }

    public ObservableList<HostAndPort> getHostAndPortsData() {
        return hostAndPortsData;
    }

}
