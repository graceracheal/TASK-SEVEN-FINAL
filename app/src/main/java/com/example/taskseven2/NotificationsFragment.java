package com.example.taskseven2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.taskseven2.databinding.FragmentNotificationsBinding;

import java.util.ArrayList;
import java.util.List;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;
    private NotificationAdapter adapter;
    private List<NotificationItem> notificationList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentNotificationsBinding.inflate(inflater, container, false);

        binding.recyclerNotifications.setLayoutManager(new LinearLayoutManager(getContext()));

        createSampleNotifications();

        adapter = new NotificationAdapter(notificationList);
        binding.recyclerNotifications.setAdapter(adapter);

        return binding.getRoot();
    }

    private void createSampleNotifications() {
        notificationList = new ArrayList<>();
        notificationList.add(new NotificationItem(" Update", "A new system update is available for your device", "Just now"));
        notificationList.add(new NotificationItem("New Message", "You have received a new message from Grace", "30 minutes ago"));
        notificationList.add(new NotificationItem("Friend Request", "Sarah sent you a friend request", "1 hour ago"));
        notificationList.add(new NotificationItem("Payment Received", "You have received 200,000 from sarah", "2 hours ago"));
        notificationList.add(new NotificationItem("Reminder", "Don't forget your meeting tomorrow at 9 AM", "Yesterday"));
        notificationList.add(new NotificationItem("App Update", "TASKSEVEN2 app has been updated to version 2.0", "2 days ago"));
        notificationList.add(new NotificationItem("Storage Warning", "Your device storage is almost full", "3 days ago"));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}