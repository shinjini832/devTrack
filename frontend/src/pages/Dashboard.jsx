import { useEffect, useState } from "react";
import API from "../services/api";

function Dashboard() {

 const user = JSON.parse(
  localStorage.getItem("user")
);

const userId = user?.id;

  const [streak, setStreak] = useState(0);

  const [weeklyData, setWeeklyData] = useState({
    totalHours: 0,
    totalTasks: 0,
    averageHours: 0,
  });

  const [leaderboard, setLeaderboard] = useState([]);
  const [logs, setLogs] = useState([]);

  const [formData, setFormData] = useState({
    description: "",
    hoursCoded: "",
    tasksCompleted: "",
    date: "",
  });

  // Fetch dashboard data
  useEffect(() => {

    fetchStreak();
    fetchWeeklyAnalytics();
    fetchLeaderboard();
    fetchLogs();

  }, []);

  // ---------------- STREAK ----------------

  const fetchStreak = async () => {

    try {

      const response = await API.get(
        `/logs/streak/${userId}`
      );

      setStreak(response.data.currentStreak);

    } catch (err) {

      console.log(err);
    }
  };

  // ---------------- WEEKLY ANALYTICS ----------------

  const fetchWeeklyAnalytics = async () => {

    try {

      const response = await API.get(
        `/logs/weekly/${userId}`
      );

      setWeeklyData(response.data);

    } catch (err) {

      console.log(err);
    }
  };

  // ---------------- LEADERBOARD ----------------

  const fetchLeaderboard = async () => {

    try {

      const response = await API.get(
        "/logs/leaderboard"
      );

      setLeaderboard(response.data);

    } catch (err) {

      console.log(err);
    }
  };
  const fetchLogs = async () => {

  try {

    const response = await API.get(
      `/logs/user/${userId}`
    );

    setLogs(response.data);

  } catch (err) {

    console.log(err);
  }
};

  // ---------------- HANDLE FORM ----------------

  const handleChange = (e) => {

    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    });
  };

  // ---------------- ADD ACTIVITY ----------------

  const handleSubmit = async (e) => {

    e.preventDefault();

    try {

      const payload = {
        ...formData,
        userId: userId,
      };

      await API.post(
        "/logs",
        payload
      );

      alert("Activity added!");

      fetchStreak();
      fetchWeeklyAnalytics();
      fetchLeaderboard();
      fetchLogs();

      setFormData({
        description: "",
        hoursCoded: "",
        tasksCompleted: "",
        date: "",
      });

    } catch (err) {

      console.log(err);
    }
  };

  return (

    <div className="min-h-screen bg-gray-100 p-6">

      <h1 className="text-4xl font-bold mb-8">
       Welcome, {user?.name} 
      </h1>
       <button
    onClick={() => {

      localStorage.removeItem("user");
      window.location.href = "/";

    }}
    className="bg-black text-white px-4 py-2 rounded-lg"
  >
    Logout
  </button>

      {/* TOP CARDS */}

      <div className="grid md:grid-cols-3 gap-6 mb-8">

        <div className="bg-white p-6 rounded-2xl shadow">
          <h2 className="text-xl font-semibold mb-2">
            Current Streak
          </h2>

          <p className="text-4xl font-bold">
            {streak} days
          </p>
        </div>

        <div className="bg-white p-6 rounded-2xl shadow">
          <h2 className="text-xl font-semibold mb-2">
            Weekly Hours
          </h2>

          <p className="text-4xl font-bold">
            {weeklyData.totalHours}
          </p>
        </div>

        <div className="bg-white p-6 rounded-2xl shadow">
          <h2 className="text-xl font-semibold mb-2">
            Tasks Completed
          </h2>

          <p className="text-4xl font-bold">
            {weeklyData.totalTasks}
          </p>
        </div>

      </div>

      {/* MAIN GRID */}

      <div className="grid md:grid-cols-2 gap-6">

        {/* ADD ACTIVITY */}

        <div className="bg-white p-6 rounded-2xl shadow">

          <h2 className="text-2xl font-bold mb-4">
            Add Activity
          </h2>

          <form onSubmit={handleSubmit}>

            <input
              type="text"
              name="description"
              placeholder="Description"
              value={formData.description}
              onChange={handleChange}
              className="w-full border p-3 rounded-lg mb-4"
              required
            />

            <input
              type="number"
              name="hoursCoded"
              placeholder="Hours coded"
              value={formData.hoursCoded}
              onChange={handleChange}
              className="w-full border p-3 rounded-lg mb-4"
              required
            />

            <input
              type="number"
              name="tasksCompleted"
              placeholder="Tasks completed"
              value={formData.tasksCompleted}
              onChange={handleChange}
              className="w-full border p-3 rounded-lg mb-4"
              required
            />

            <input
              type="date"
              name="date"
              value={formData.date}
              onChange={handleChange}
              className="w-full border p-3 rounded-lg mb-6"
              required
            />

            <button
              type="submit"
              className="w-full bg-black text-white py-3 rounded-lg"
            >
              Add Activity
            </button>

          </form>

        </div>
        {/* ACTIVITY HISTORY */}

<div className="bg-white p-6 rounded-2xl shadow mt-8">

  <h2 className="text-2xl font-bold mb-4">
    Activity History 📅
  </h2>

  <div className="overflow-x-auto">

    <table className="w-full border-collapse">

      <thead>

        <tr className="border-b">

          <th className="text-left p-3">Date</th>

          <th className="text-left p-3">
            Description
          </th>

          <th className="text-left p-3">
            Hours
          </th>

          <th className="text-left p-3">
            Tasks
          </th>

        </tr>

      </thead>

      <tbody>

        {logs.map((log) => (

          <tr
            key={log.id}
            className="border-b hover:bg-gray-50"
          >

            <td className="p-3">
              {log.date}
            </td>

            <td className="p-3">
              {log.description}
            </td>

            <td className="p-3">
              {log.hoursCoded}
            </td>

            <td className="p-3">
              {log.tasksCompleted}
            </td>

          </tr>
        ))}

      </tbody>

    </table>

  </div>

</div>

        {/* LEADERBOARD */}

        <div className="bg-white p-6 rounded-2xl shadow">

          <h2 className="text-2xl font-bold mb-4">
            Leaderboard 🏆
          </h2>

          <div className="space-y-4">

            {leaderboard.map((user, index) => (

              <div
                key={index}
                className="flex justify-between border-b pb-2"
              >

                <span className="font-medium">
                  {user.userName}
                </span>

                <span className="font-bold">
                  {user.score}
                </span>

              </div>
            ))}

          </div>

        </div>

      </div>

    </div>
  );
}

export default Dashboard;