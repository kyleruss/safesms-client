//======================================
//  Kyle Russell
//  AUT University 2016
//  Highly Secured Systems A2
//======================================

package com.kyleruss.hssa2.client.core;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kyleruss.hssa2.client.R;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.List;

public class MessageListAdapter extends ArrayAdapter<Message>
{
    private final Activity context;
    private List<Message> messages;

    public MessageListAdapter(List<Message> messages, Activity context)
    {
        super(context, R.layout.user_list_row, messages);
        this.messages       =   messages;
        this.context        =   context;
    }

    public void setList(Collection<Message> userList)
    {
        super.clear();
        super.addAll(userList);
        super.notifyDataSetChanged();
        messages    =   (List<Message>) userList;
    }

    public List<Message> getMessageList()
    {
        return messages;
    }

    public Message getMessageAt(int pos)
    {
        return messages.get(pos);
    }

    public View getView(int position, View view, ViewGroup parent)
    {
        LayoutInflater inflater     =   context.getLayoutInflater();
        View viewRow                =   inflater.inflate(R.layout.message_list_row, null, true);
        Message message             =   messages.get(position);
        ImageView statusImageView   =   (ImageView) viewRow.findViewById(R.id.messageImageview);
        TextView messageInfoView    =   (TextView) viewRow.findViewById(R.id.messageInfoView);
        String recvDate             =   message.recvDateToString();

        messageInfoView.setText("From: " + message.getFrom() + "\nReceived at: " + recvDate);
        statusImageView.setImageResource(message.isRead()? R.drawable.notification_read : R.drawable.notification_unread);
        return viewRow;
    }
}