//======================================
//  Kyle Russell
//  AUT University 2016
//  Highly Secured Systems A2
//======================================

package com.kyleruss.hssa2.client.com.kyleruss.hssa2.client.fragment;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.kyleruss.hssa2.client.R;
import com.kyleruss.hssa2.client.core.Message;
import com.kyleruss.hssa2.client.core.MessageListAdapter;
import com.kyleruss.hssa2.client.core.MessageManager;
import com.kyleruss.hssa2.client.core.User;

import java.util.List;

import static android.R.id.message;


public class MessageInspectFragment extends Fragment implements View.OnClickListener
{
    private Message currentMessage;

    public MessageInspectFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        getActivity().getActionBar().setTitle("Read message");
        View view = inflater.inflate(R.layout.fragment_message_inspect, container, false);
        ImageView decryptBtn    =   ((ImageView) view.findViewById(R.id.decryptBtn));
        ImageView replyBtn      =   ((ImageView) view.findViewById(R.id.replyBtn));

        decryptBtn.setOnClickListener(this);
        replyBtn.setOnClickListener(this);

        Bundle data =   getArguments();
        if(data != null)
        {
            currentMessage  =   (Message) data.getSerializable("message");
            ((TextView) view.findViewById(R.id.fromText)).setText("From: " + currentMessage.getFrom());
            ((TextView) view.findViewById(R.id.recvText)).setText("Received: " + currentMessage.recvDateToString());
            ((TextView) view.findViewById(R.id.msgContent)).setText(currentMessage.getContent());

            if(currentMessage.isDecrypted())
                ((ImageView) view.findViewById(R.id.decryptBtn)).setImageResource(R.drawable.decrypt_unavail_image);
        }

        return view;
    }

    public void replyMessage()
    {
        Fragment smsFragment    =   new SendSMSFragment();
        Bundle data             =   new Bundle();
        data.putString("phoneID", currentMessage.getFrom());
        smsFragment.setArguments(data);

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.container, smsFragment)
                .addToBackStack(null)
                .commit();
    }

    public void decryptMessage()
    {
        if(currentMessage.isDecrypted())
            Toast.makeText(getActivity().getApplicationContext(), "Message is already decrypted", Toast.LENGTH_SHORT).show();

        else
        {

        }
    }

    @Override
    public void onClick(View v)
    {
        int id  =   v.getId();

        if(id == R.id.replyBtn)
            replyMessage();

        else if(id == R.id.decryptBtn)
            decryptMessage();
    }
}