package viewer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;


class AdminDialog extends javax.swing.JDialog
                  implements java.awt.event.ActionListener
{
    private Container container = this.getContentPane();
    private Frame parent = null;
    private JButton buttonOk = new JButton();
    private JButton buttonCancel = new JButton();

    /** Button texts: **/
    private static final String saveButtonText    = "Save";
    private static final String cancelButtonText  = "Cancel";

    /** **/
    private final int dialogDefaultWidth = 575;
    private final int dialogDefaultHeight = 200;

    /** Commands: **/
    // TODO: --> to ENUMS's
    private static final String saveChangesCommand = "SAVE_CHANGES_COMMAND";
    private static final String cancelCommand      = "CANCEL_COMMAND";


    public AdminDialog(ViewerWindow parent, String title)
    {
        super(parent, title, true);
        this.parent = parent;
        this.container = this.getContentPane();
        container.setLayout(null);
    }

    public void OpenDialog()
    {
        this.setBounds(parent.getX() + 50, parent.getY() + 50, dialogDefaultWidth, dialogDefaultHeight);
        // CreateNodeConfigBlock();
        // CreateTopicsConfigBlock();
        CreateOKButton();
        CreateCancelButton();

        this.setResizable(false);
        this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    private void CreateOKButton()
    {
        buttonOk.setText(saveButtonText);
        buttonOk.setActionCommand(saveChangesCommand);
        buttonOk.addActionListener(this);
        container.add(buttonOk);
        buttonOk.setBounds(this.getWidth() - 110, this.getHeight() - 65, 90, 24);
    }

    private void CreateCancelButton()
    {
        buttonCancel.setText(cancelButtonText);
        buttonCancel.setActionCommand(cancelCommand);
        buttonCancel.addActionListener(this);
        container.add(buttonCancel);
        buttonCancel.setBounds(this.getWidth() - 120 - buttonOk.getWidth(), this.getHeight() - 65, 90, 24);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent)
    {
        switch (actionEvent.getActionCommand()) {
            case saveChangesCommand :
                HandleSaveOperation();
                break;
            case cancelCommand:
                /* TODO */;
                break;
            default:
                System.out.println(actionEvent.getActionCommand());
                break;
        }

        System.out.println("actionPerformed 1");

        //notify();
        dispose();
    }

    private void HandleSaveOperation()
    {
        System.out.println("HandleSaveOperation 1");
        /*
        consumer.clearTopics();
        Arrays.asList(topicListField.getText().split(",")).forEach(node -> {
            consumer.addTopic(node.trim());
        });

        Arrays.asList(nodesListField.getText().split(",")).forEach(server -> {
            System.out.println(server.trim());
        });

        consumer.Stop();
        consumer.start();
        */
    }
}
