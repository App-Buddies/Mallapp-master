package interviewmaster.admin.interview.com.mallapplication;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


class MyAdapte extends RecyclerView.Adapter<MyAdapte.ViewHldr> {
    Context context;
    List<Example> array;
    LayoutInflater layoutInflater;
    Communicator communicator;
    Employee item;

    public MyAdapte(Context activity, List<Example> array) {
        this.context = activity;
        this.array = array;
        layoutInflater = LayoutInflater.from(context.getApplicationContext());
    }

    @Override
    public ViewHldr onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.custom_layout, parent, false);
        ViewHldr viewHldr = new ViewHldr(view, parent.getContext(), array);
        return viewHldr;
    }

    int i = 0;

    @Override
    public void onBindViewHolder(final ViewHldr holder, final int position) {
        item = array.get(0).getEmployee().get(position); //this should give an error, you are trying to get employee object from s list of example
        holder.firstName.setText(item.getFirstName());
        //holder.lastName.setText(item.getLastName());
        for (i = 0; i < item.getSkills().get(0).getExtraCurricular().size(); i++) {
            //  String joinString1=String.join("-","welcome","to","javatpoint");
            holder.lastName.append(item.getSkills().get(0).getExtraCurricular().get(i) + " ");

        }
        for (i = 0; i < item.getSkills().get(0).getTechnical().size(); i++) {
            holder.designation.append(item.getSkills().get(0).getTechnical().get(i));

        }
        holder.city.setText(item.getCity());

        if (!TextUtils.isEmpty(item.getImageURL())) {
            Picasso.with(holder.context)
                    .load(item.getImageURL())
                    .networkPolicy(NetworkPolicy.OFFLINE) //why is the network policy offline??
                    .into(holder.imageView, new Callback() {
                        @Override
                        public void onSuccess() {
                            //holder.relative.setBackground(img.getDrawable());
                        }

                        @Override
                        public void onError() {
                            //this part is basically redundant you can just your .error in main function
                            Picasso.with(context)
                                    .load(item.getImageURL())
                                    .error(R.mipmap.ic_launcher)
                                    .into(holder.imageView, new Callback() {
                                        @Override
                                        public void onSuccess() {

                                        }

                                        @Override
                                        public void onError() {
                                            Log.v("Picasso", "Could not fetch image");
                                        }
                                    });
                        }
                    });
        } else {
            Picasso.with(context)
                    .load(R.mipmap.ic_launcher)
                    .into(holder.imageView);
        }
    }

    @Override
    public int getItemCount() {
        if (array.get(0).getEmployee().size() == 0) {
            return 0;
        } else {
            return array.get(0).getEmployee().size();
        }

    }

    public class ViewHldr extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView firstName, lastName, designation, city;
        List<Example> array = new ArrayList<>();
        Context context;
        ImageView imageView;

        public ViewHldr(View itemView, Context activity, List<Example> array) {
            super(itemView);
            this.context = activity;
            this.array = array;
            firstName = (TextView) itemView.findViewById(R.id.textFirstName);
            lastName = (TextView) itemView.findViewById(R.id.textSecondName);
            designation = (TextView) itemView.findViewById(R.id.textDesignation);
            city = (TextView) itemView.findViewById(R.id.textCity);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            //itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Employee array = this.array.get(0).getEmployee().get(0);
            String firstname = array.getFirstName();
            communicator.respond(firstname);
        }
    }


}